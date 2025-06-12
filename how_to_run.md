./gradlew run --args="HeapRWFinder sample-rw.json app-debug.apk"

mv demo/Android/Instrumented/app-debug.apk app-debug-step1.apk

./gradlew run --args="DetermineCacheability app-debug-step1.apk"

mv demo/Android/Instrumented/app-debug-step1.apk app-debug-step2.apk

mv demo/Android/Instrumented/non_determinism_output.txt non_determinism_output.txt

./gradlew run --args="ComputeCacher app-debug-step2.apk sample-rw.json"

mv demo/Android/Instrumented/app-debug-step2.apk app-debug-step3.apk

/Users/musanto/Library/Android/sdk/build-tools/34.0.0/zipalign -f 4 app-debug-step3.apk app-debug-aligned.apk 
# replace 34.0.0 with whatever version you have available

~/Library/Android/sdk/build-tools/34.0.0/apksigner sign --ks ~/.android/debug.keystore --ks-key-alias \ 
	androiddebugkey --ks-pass pass:android app-debug-aligned.apk


