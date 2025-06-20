## To build Floo and run the three main tasks from the instrumentation phase (and to sign and align the resulting .apk file)

```bash

./gradlew clean build

./gradlew run --args="HeapRWFinder sample-rw.json app-debug.apk"

mv demo/Android/Instrumented/app-debug.apk app-debug-step1.apk

./gradlew run --args="DetermineCacheability app-debug-step1.apk"

mv demo/Android/Instrumented/app-debug-step1.apk app-debug-step2.apk

mv demo/Android/Instrumented/non_determinism_output.txt non_determinism_output.txt

./gradlew run --args="ComputeCacher app-debug-step2.apk sample-rw.json"

mv demo/Android/Instrumented/app-debug-step2.apk app-debug-step3.apk

# replace 34.0.0 below with whatever version you have available
/Users/musanto/Library/Android/sdk/build-tools/34.0.0/zipalign -f 4 app-debug-step3.apk app-debug-aligned.apk 

~/Library/Android/sdk/build-tools/34.0.0/apksigner sign --ks ~/.android/debug.keystore --ks-key-alias \ 
	androiddebugkey --ks-pass pass:android app-debug-aligned.apk
```


## To copy paste smali_classes contents from the helper.apk into the instrumented .apk of the app

```bash
deconstruct the .apk file to get dir with smali files:
# apktool d app-debug-aligned.apk -o app-debug-aligned-smali

mkdir -p app-debug-aligned-smali/smali/com/example/ && \
cp -R helper-smali/smali_classes2/com/example/asyncprinter \
      app-debug-aligned-smali/smali/com/example/
# Note: change smali in the first line to smali_classesX as required

cd app-debug-aligned-smali
apktool b . -o ../app-with-helper.apk
# to rebuild the .apk

# then: align
cd .. # go back to the root
~/Library/Android/sdk/build-tools/34.0.0/zipalign -f 4 app-with-helper.apk app-with-helper-aligned.apk

# sign
~/Library/Android/sdk/build-tools/34.0.0/apksigner sign \
  --ks ~/.android/debug.keystore \
  --ks-key-alias androiddebugkey \
  --ks-pass pass:android \
  app-with-helper-aligned.apk
# note, the file is signed in-place: output app-with-helper-aligned.apk

# start device
~/Library/Android/sdk/emulator/emulator -avd Medium_Phone_API_36.0

# verify adb sees it
~/Library/Android/sdk/platform-tools/adb devices
# output: emulator-5554   device

# install app
~/Library/Android/sdk/platform-tools/adb install -r app-with-helper-aligned.apk
```
