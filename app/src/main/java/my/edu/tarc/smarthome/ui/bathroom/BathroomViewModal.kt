package my.edu.tarc.smarthome.ui.bathroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BathroomViewModal : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bathroom Fragment"
    }
    val text: LiveData<String> = _text
}