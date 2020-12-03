package my.edu.tarc.smarthome.ui.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PanicViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is for Emergency Use Only"
    }
    val text: LiveData<String> = _text
}