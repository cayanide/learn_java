#!/bin/bash

# ---------- CONFIG ----------
OUT_DIR="classes"
BACKUP_DIR="backups"
SUCCESS_COLOR="\033[0;32m"
ERROR_COLOR="\033[0;31m"
INFO_COLOR="\033[1;34m"
NC="\033[0m"

# ---------- Spinner ----------
spin() {
  local pid=$1
  local delay=0.1
  local spinstr='|/-\'
  while kill -0 $pid 2>/dev/null; do
    local temp=${spinstr#?}
    printf " [%c]  " "$spinstr"
    local spinstr=$temp${spinstr%"$temp"}
    sleep $delay
    printf "\b\b\b\b\b\b"
  done
  printf "       \b\b\b\b\b\b"
}

# ---------- Start ----------
echo -e "${INFO_COLOR}📦 Java Runner Script${NC}"

if [ -z "$1" ]; then
  echo -e "${ERROR_COLOR}❌ Please provide the Java class name (without .java).${NC}"
  echo "Usage: ./run.sh ClassName"
  exit 1
fi

CLASS_NAME="$1"
JAVA_FILE="${CLASS_NAME}.java"

if [ ! -f "$JAVA_FILE" ]; then
  echo -e "${ERROR_COLOR}❌ File not found: ${JAVA_FILE}${NC}"
  exit 1
fi

MEMORY_SIGNATURE='System.out.println("Total Memory:'
mkdir -p "$BACKUP_DIR"

# ---------- Inject Memory Snippet ----------
if ! grep -q "$MEMORY_SIGNATURE" "$JAVA_FILE"; then
  echo -e "${INFO_COLOR}➕ Injecting memory usage code into ${JAVA_FILE}${NC}"

  BACKUP_FILE="${BACKUP_DIR}/${CLASS_NAME}.java.bak"
  cp "$JAVA_FILE" "$BACKUP_FILE"
  TEMP_FILE="${JAVA_FILE}.tmp"

  awk '
    BEGIN { in_main=0; brace_level=0; injected=0 }
    /public[ \t]+static[ \t]+void[ \t]+main[ \t]*\(.*\)/ {
      in_main=1;
    }
    {
      if (in_main) {
        brace_level += gsub(/{/, "{")
        brace_level -= gsub(/}/, "}")
      }
      if (in_main && brace_level == 0 && $0 ~ /^ *}/ && !injected) {
        print "        Runtime runtime = Runtime.getRuntime();"
        print "        runtime.gc();"
        print "        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024);"
        print "        long totalMemory = runtime.totalMemory() / (1024 * 1024);"
        print "        // BEGIN_HEAP_PRINT"
        print "        System.out.println(\"Used Heap: \" + usedMemory + \" MB\");"
        print "        System.out.println(\"Total Memory: \" + totalMemory + \" MB\");"
        print "        // END_HEAP_PRINT"
        injected = 1
      }
      print
    }
  ' "$JAVA_FILE" > "$TEMP_FILE"

  if [ -s "$TEMP_FILE" ]; then
    mv "$TEMP_FILE" "$JAVA_FILE"
  else
    echo -e "${ERROR_COLOR}⚠️ Injection failed, reverting.${NC}"
    mv "$BACKUP_FILE" "$JAVA_FILE"
  fi
fi

# ---------- Compile ----------
mkdir -p "$OUT_DIR"
echo -e "${INFO_COLOR}🔧 Compiling '${JAVA_FILE}' to '${OUT_DIR}'...${NC}"
javac -d "$OUT_DIR" "$JAVA_FILE" &
spin $!

if [ $? -ne 0 ]; then
  echo -e "${ERROR_COLOR}💥 Compilation failed.${NC}"
  exit 1
fi

echo -e "${SUCCESS_COLOR}✅ Compilation successful!${NC}"

# ---------- Execute ----------
echo -e "${INFO_COLOR}🚀 Running '${CLASS_NAME}'...${NC}"
echo "----------------------------------------"

START_TIME=$(gdate +%s%N 2>/dev/null || date +%s%N)
JAVA_OUTPUT=$(java -Xmx256m -cp "$OUT_DIR" "$CLASS_NAME")
RUN_STATUS=$?
END_TIME=$(gdate +%s%N 2>/dev/null || date +%s%N)
DURATION_NS=$((END_TIME - START_TIME))
DURATION_MS=$(awk "BEGIN {printf \"%.2f\", $DURATION_NS / 1000000}")

# ---------- Output Filtering ----------
HEAP_USED=$(echo "$JAVA_OUTPUT" | grep 'Used Heap:' | cut -d':' -f2 | xargs)
TOTAL_MEM=$(echo "$JAVA_OUTPUT" | grep 'Total Memory:' | cut -d':' -f2 | xargs)
CLEAN_OUTPUT=$(echo "$JAVA_OUTPUT" | sed '/Used Heap:/d;/Total Memory:/d')

echo "$CLEAN_OUTPUT"
echo "----------------------------------------"

# ---------- Final Report ----------
if [ $RUN_STATUS -ne 0 ]; then
  echo -e "${ERROR_COLOR}❌ Runtime error. Exit code: $RUN_STATUS${NC}"
else
  echo -e "${SUCCESS_COLOR}✅ Execution successful!${NC}"
  echo -e "🧠 Time: ${DURATION_MS} ms"
  echo -e "💾 Heap Used: ${HEAP_USED:-Unavailable}"
  echo -e "📈 Total Memory: ${TOTAL_MEM:-Unavailable}"
fi

echo -e "${INFO_COLOR}🧹 Tip: run 'rm -rf ${OUT_DIR}/*' to clean class files.${NC}"
