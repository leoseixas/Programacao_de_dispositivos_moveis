package com.example.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.text.MessageFormat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnCalc.setOnClickListener(View.OnClickListener {

            validarCamposBasicos()

        })
        editNome.addTextChangedListener(clearErrorMessage(layoutEditNome))
        editAltura.addTextChangedListener(clearErrorMessage(layoutEditAltura))
        editPeso.addTextChangedListener(clearErrorMessage(layoutEditPeso))
    }



    private fun exibirMensagemErro(editText: EditText, textViewMessage: TextInputLayout, mensagem: String ) {
        textViewMessage.error = mensagem
        editText.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        editText.requestFocus();
    }

    fun exibirMensagemErroNome(){
        val mensagem = formatarMensagem("Campooooooo")
        exibirMensagemErro(editNome, layoutEditNome, mensagem)
    }

    fun exibirMensagemErroPeso(){
        val mensagem = formatarMensagem("Campooooooo")
        exibirMensagemErro(editPeso, layoutEditPeso, mensagem)
    }

    fun exibirMensagemErroAltura(){
        val mensagem = formatarMensagem("Campooooooo")
        exibirMensagemErro(editAltura, layoutEditAltura, mensagem)
    }

    private fun validarCamposBasicos(): Boolean {
        Log.e("ERRO", editNome.toString()+"dsbhfkjsdhf")
        clearErrorMessage(layoutEditNome)
        if (isEmpty(editNome.text.toString())) {
            exibirMensagemErroNome()
            return false
        }
        clearErrorMessage(layoutEditPeso)

        if (isEmpty(editPeso.text.toString())) {
            exibirMensagemErroPeso()
            return false
        }
        clearErrorMessage(layoutEditAltura)

        if (isEmpty(editAltura.text.toString())) {
            exibirMensagemErroAltura()
            return false
        }
        return true
    }

    private fun isEmpty(valor: String) = valor == ""

    private fun formatarMensagem(campo:String) : String{
        val message = "Informe o campo"
        return  MessageFormat.format(message, campo)
    }

    private fun clearErrorMessage(text:TextInputLayout): TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int ) { }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int ) {
                text.error = ""
            }
        }
    }
}
