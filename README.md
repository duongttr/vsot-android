# vsot-android
VSOT is a alternative solution for TTS Engine in Android Studio using SoundOfText API.
You can visit SOT API site right here: [https://soundoftext.com/docs](https://soundoftext.com/docs)
And visit my blog, if you want: [https://www.duongtech.com/](https://www.duongtech.com/)
## How to use?
Clone my project or just download 3 main .java files and add them to your project in [https://github.com/stephentran1909/vsot-android/tree/master/app/src/main/java/com/duongtech/vsot/VSOT](https://github.com/stephentran1909/vsot-android/tree/master/app/src/main/java/com/duongtech/vsot/VSOT)
### Main functions
Then, using `VSOT.speak(String text, String lang)` to speak the text you want.
Stop speaking by using `VSOT.stop()`
Check if VSOT is speaking by using `VSOT.isSpeaking()`
### Supported Languages
VSOT supports **55 languages**, see API site above for more details.
### Requirement
Internet Connection, please!
