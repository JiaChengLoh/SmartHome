package my.edu.tarc.smarthome.ui.falldown

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FalldownViewModal : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is falldown Fragment"
    }
    val text: LiveData<String> = _text


}