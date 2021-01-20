package bigchris.studying.speechrecognizercontroller

interface SpeechRecognizerControllerListener {

    fun onSpeechRecognitionError(errorCode: Int, errorString: String)

    fun onSpeechRecognitionResults(resultStrings: ArrayList<String>)

    fun onSpeechRecognitionPartialResults(resultStrings: ArrayList<String>)

    fun onSpeechRecognitionStarted()

    fun onSpeechRecognitionStopped()

}