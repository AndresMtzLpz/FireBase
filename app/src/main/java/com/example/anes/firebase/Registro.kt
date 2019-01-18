package com.example.anes.firebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        val auth= FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance()
        val dbReference = db.reference

        btn_registrarR.setOnClickListener{
            auth.createUserWithEmailAndPassword(et_correoR.text.toString(),et_contraseñaR.text.toString()).addOnCompleteListener(this){
                task ->
                if(!task.isSuccessful){
                    Toast.makeText(this,"No se pudo crear el usuario :(",Toast.LENGTH_SHORT).show()
                    Log.w("CreateAccount","createUser:failure",task.exception)
                }else{
                    Toast.makeText(this,"Usuario Creado",Toast.LENGTH_SHORT).show()
                    val userActual : FirebaseUser? = auth.currentUser
                    val userUID = userActual?.uid
                    dbReference.child("Users").child(userUID.toString()).setValue(et_nombreR.text.toString())
                    userActual?.sendEmailVerification()?.addOnCompleteListener(this){
                        task ->
                        if (!task.isSuccessful){
                            Toast.makeText(this,"No se pudo enviar correo de verificación :(", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"Se ha enviado un correo de verificación",Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }

        }
    }
}
