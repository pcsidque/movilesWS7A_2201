package com.example.app_sem05_camara_20220421

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btRequest = findViewById<Button>(R.id.btRequest)
        btRequest.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        != PackageManager.PERMISSION_GRANTED)
        {
            //no tengo permiso
            requestCameraPermission()
        }
        else{
            //tengo permiso
            Toast.makeText(this, "Ya cuenta con permiso de la camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
        //el usuario ha rechazado el permiso anteriormente?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
        {
            Toast.makeText(this, "Rechazó el permiso antes. Habilitelo manualmente", Toast.LENGTH_LONG).show()
        }
        else{
            //1ra vez q le piden el permiso
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            CAMERA_REQUEST_CODE ->{
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //permiso ACEPTADO
                    Toast.makeText(this, "permiso ACEPTADO para la camara", Toast.LENGTH_LONG).show()
                    //Aqui pondría toda la funcionalidad
                    //llamaría a otro metodo
                }
                else{
                    Toast.makeText(this, "permiso NEGADO", Toast.LENGTH_LONG).show()
                }
                return
            }
        }
    }
}