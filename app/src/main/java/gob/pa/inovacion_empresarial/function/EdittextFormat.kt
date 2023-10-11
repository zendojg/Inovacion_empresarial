package gob.pa.inovacion_empresarial.function

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import gob.pa.inovacion_empresarial.model.ModelTexWatchers
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.ParseException

object EdittextFormat {
    fun edittextBigSum(
        txt: EditText,
        editTexts: List<EditText>,
        resultEditText: EditText
    ): ModelTexWatchers {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se requiere acción antes del cambio de texto.
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No se requiere acción en el cambio de texto.
            }
            override fun afterTextChanged(s: Editable?) {
                txt.removeTextChangedListener(this)
                val decimalFormat = DecimalFormat("#,###")
                try {
                    val originalText = if (!s.isNullOrEmpty()) s.toString() else "0"
                    val longValue = decimalFormat.parse(originalText)?.toLong() ?: 0
                    val formattedText = decimalFormat.format(longValue)
                    txt.setText(formattedText)
                    txt.setSelection(formattedText.length)
                } catch (e: ParseException) { txt.setText("0") }
                txt.addTextChangedListener(this)
                try {
                    // Calcular la suma de los valores en los EditText
                    val sum = editTexts.fold(BigDecimal.ZERO) { acc, editText ->
                        val text = editText.text.toString().replace(",", "")
                        if (text.isNotBlank()) { acc.add(BigDecimal(text)) }
                        else { acc }
                    }
                    val formattedSum = decimalFormat.format(sum)
                    resultEditText.setText(formattedSum)

                } catch (e: ParseException) {
                    println(e)
                }
            }
        }
        txt.addTextChangedListener(textWatcher)
        return ModelTexWatchers(txt, textWatcher)
    }

//    fun edittextSimpleSum(
//        txt: EditText,
//        editTexts: List<EditText>,
//        resultEditText: EditText
//    ): ModelTexWatchers {
//        val textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // No se requiere acción antes del cambio de texto.
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // No se requiere acción en el cambio de texto.
//            }
//            override fun afterTextChanged(s: Editable?) {
//                txt.removeTextChangedListener(this)
//                try {
//                    val originalText = if (!s.isNullOrEmpty()) s.toString() else "0"
//                    val intValue = originalText.toIntOrNull() ?: 0
//                    txt.setText(intValue.toString())
//                    txt.setSelection(originalText.length)
//                } catch (e: NumberFormatException) { txt.setText("0") }
//                txt.addTextChangedListener(this)
//                try {
//                    val sum = editTexts.fold(0) { acc, editText ->
//                        val text = editText.text.toString().replace(",", "")
//                        if (text.isNotBlank()) {
//                            acc + text.toInt()
//                        } else { acc }
//                    }
//                    resultEditText.setText(sum.toString())
//                } catch (e: NumberFormatException) { println(e) }
//            }
//        }
//        txt.addTextChangedListener(textWatcher)
//        return ModelTexWatchers(txt, textWatcher)
//    }

    fun edittextMiles(txt: EditText): ModelTexWatchers {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No se requiere acción antes del cambio de texto.
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No se requiere acción en el cambio de texto.
            }
            override fun afterTextChanged(s: Editable?) {
                txt.removeTextChangedListener(this)
                val decimalFormat = DecimalFormat("#,###")
                try {
                    val originalText = if (!s.isNullOrEmpty()) s.toString() else "0"
                    val longValue = decimalFormat.parse(originalText)?.toLong() ?: 0
                    val formattedText = decimalFormat.format(longValue)
                    txt.setText(formattedText)
                    txt.setSelection(formattedText.length)
                } catch (e: ParseException) { txt.setText("0") }
                txt.addTextChangedListener(this)
            }
        }
        txt.addTextChangedListener(textWatcher)
        return ModelTexWatchers(txt, textWatcher)
    }
}