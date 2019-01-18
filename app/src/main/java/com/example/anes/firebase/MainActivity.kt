package com.example.anes.firebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val auth= FirebaseAuth.getInstance()

        btn_ingresar.setOnClickListener{
            auth.signInWithEmailAndPassword(et_correo.text.toString(),et_contraseña.text.toString()).addOnCompleteListener(this){
                task ->
                if (!task.isSuccessful){
                    Toast.makeText(this,"Datos erroneos",Toast.LENGTH_SHORT).show()
                }else{
                    val i = Intent(this,ActividadChat::class.java)
                    startActivity(i)

                }
            }

        }
        btn_olvido.setOnClickListener{
            val i1 = Intent(this,Contrasena::class.java)
            startActivity(i1)
        }
        btn_registrar.setOnClickListener{
            val i2 = Intent(this,Registro::class.java)
            startActivity(i2)
        }
    }

}
