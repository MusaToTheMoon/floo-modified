./gradlew clean build

./gradlew run --args="HeapRWFinder sample-rw.json app-debug.apk"

mv demo/Android/Instrumented/app-debug.apk app-debug-step1.apk

./gradlew run --args="DetermineCacheability app-debug-step1.apk"

mv demo/Android/Instrumented/app-debug-step1.apk app-debug-step2.apk

mv demo/Android/Instrumented/non_determinism_output.txt non_determinism_output.txt

./gradlew run --args="ComputeCacher app-debug-step2.apk sample-rw.json"

mv demo/Android/Instrumented/app-debug-step2.apk app-debug-step3.apk
