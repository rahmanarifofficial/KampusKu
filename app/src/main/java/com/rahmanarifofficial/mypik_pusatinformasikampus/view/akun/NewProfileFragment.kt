package com.rahmanarifofficial.mypik_pusatinformasikampus.view.akun

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rahmanarifofficial.mypik_pusatinformasikampus.MainActivity
import com.rahmanarifofficial.mypik_pusatinformasikampus.R
import com.rahmanarifofficial.mypik_pusatinformasikampus.presenter.AkunPresenter
import com.rahmanarifofficial.mypik_pusatinformasikampus.util.AuthPreferences
import kotlinx.android.synthetic.main.fragment_new_profile.*
import org.jetbrains.anko.support.v4.toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class NewProfileFragment : Fragment(), NewProfileView {

    private lateinit var foto: Uri
    private val GALLERY = 1
    private val CAMERA = 2

    companion object {
        private val IMAGE_DIRECTORY = "/kampusku"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as MainActivity).setActionBarTitle(getString(R.string.buat_akun))
        return inflater.inflate(R.layout.fragment_new_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        foto = Uri.parse("null")
        iv_foto_profil_new_akun.setOnClickListener {
            showPickerImage()
        }
        btn_daftar_new_akun.setOnClickListener {
            val prefs = AuthPreferences(activity!!)
            val email = prefs.getEmail()
            val password = prefs.getPassword()

            et_nama_new_akun.setError(null)
            et_telepon_new_akun.setError(null)
            et_alamat_new_akun.setError(null)
            et_asal_sekolah_new_akun.setError(null)

            val nama = et_nama_new_akun.text.toString()
            val noTelp = et_telepon_new_akun.text.toString()
            val alamat = et_alamat_new_akun.text.toString()
            val sekolah = et_asal_sekolah_new_akun.text.toString()
            val instagram = et_instagram_new_akun.text.toString()
            val facebook = et_facebook_new_akun.text.toString()

            var cancel = false

            if (TextUtils.isEmpty(nama)) {
                et_nama_new_akun.setError(getString(R.string.error_field_required))
                cancel = true
            }

            if (TextUtils.isEmpty(noTelp)) {
                et_telepon_new_akun.setError(getString(R.string.error_field_required))
                cancel = true
            }

            if (TextUtils.isEmpty(alamat)) {
                et_alamat_new_akun.setError(getString(R.string.error_field_required))
                cancel = true
            }

            if (TextUtils.isEmpty(sekolah)) {
                et_asal_sekolah_new_akun.setError(getString(R.string.error_field_required))
                cancel = true
            }

            if (!cancel) {
                AkunPresenter.insertPengguna(
                    this,
                    email,
                    password,
                    nama,
                    noTelp,
                    alamat,
                    sekolah,
                    instagram,
                    facebook,
                    foto
                )
            }
        }
    }

    private fun showPickerImage() {
        val pictureDialog = AlertDialog.Builder(activity!!)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(
            pictureDialogItems
        ) { dialog, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY) {
            if (data != null) {
                val foto = data.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, foto)
                    val path = saveImage(bitmap)
                    toast("Image Saved")
                    iv_foto_profil_new_akun!!.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                    toast("Failed")
                }
                Log.d("TESTGALLERY", foto?.toString())
            }
        } else if (requestCode == CAMERA) {
            val thumbnail = data!!.extras!!.get("data") as Bitmap
            iv_foto_profil_new_akun!!.setImageBitmap(thumbnail)
            saveImage(thumbnail)
            toast("Image Saved")
        }

    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMAGE_DIRECTORY
        )
        // have the object build the directory structure, if needed.
        Log.d("fee", wallpaperDirectory.toString())
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs()
        }
        try {
            Log.d("heel", wallpaperDirectory.toString())
            val f = File(
                wallpaperDirectory, ((Calendar.getInstance()
                    .getTimeInMillis()).toString() + ".jpg")
            )
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                activity,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null
            )
            fo.close()
            foto = Uri.fromFile(f.absoluteFile)
            Log.d("TESTCAMERA", "File Saved::--->" + foto.toString())
            return f.getAbsolutePath()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }

        return ""
    }

    override fun showProgress() {
        pb_registrasi.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_registrasi.visibility = View.INVISIBLE
    }

    override fun updateUI() {
        val fm = fragmentManager
        val ft = fm!!.beginTransaction()
        ft.replace(R.id.main_container, ProfileFragment())
        ft.commit()
    }

}
