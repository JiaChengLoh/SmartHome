package my.edu.tarc.smarthome.ui.medicine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MedicineViewModal : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is medicineFragment"
    }
    val text: LiveData<String> = _text
}