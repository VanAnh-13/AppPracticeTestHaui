package com.example.nonameapp.ui

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nonameapp.base.BaseFragment
import com.example.nonameapp.data.source.local.SharedPreferencesManager
import com.example.nonameapp.databinding.FragmentEditProfileBinding
import com.example.nonameapp.request.ChangePasswordRequest
import com.example.nonameapp.request.UpdateProfileRequest
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EditProfileFragment :
    BaseFragment<FragmentEditProfileBinding>(FragmentEditProfileBinding::inflate) {

    private val REQUEST_IMAGE_CAPTURE = 1
    private lateinit var currentPhotoPath: String
    private val REQUEST_IMAGE_PICK = 2
    private var selectedImageUri: Uri? = null

    override val viewModel: EditProfileViewModel
        get() = ViewModelProvider(this)[EditProfileViewModel::class.java]

    override fun initData() {
        Glide.with(requireContext())
            .load(SharedPreferencesManager.getUserInfo(requireContext())?.avatar)
            .into(binding.profileImage)
    }

    override fun bindData() {
    }

    override fun observeData() {
    }

    override fun setOnClick() {
        binding.saveChanges.setOnClickListener {
            selectedImageUri?.let { uri ->
                val file = uriToFile(uri)
                file?.let { imageFile ->
                    SharedPreferencesManager.getToken(requireContext())?.let { token ->
                        viewModel.updateProfile(
                            token,
                            UpdateProfileRequest(
                                binding.editUserName.text.toString(),
                                binding.editLinks.text.toString(),
                                imageFile
                            )
                        )
                    }
                }
            }
            if (checkPassword(
                    binding.editPassword.text.toString(),
                    binding.editConfirmPass.text.toString()
                )
                && binding.editPassword.text.toString().isNotEmpty()
            ) {
                SharedPreferencesManager.getToken(requireContext())?.let { it1 ->
                    viewModel.changePassword(
                        it1,
                        ChangePasswordRequest(
                            binding.oldPassword.text.toString(),
                            binding.editPassword.text.toString(),
                            binding.editConfirmPass.text.toString()
                        )
                    )
                }
            }
        }

        binding.changePhoto.setOnClickListener {
            showImagePickerDialog()
        }
    }

    fun showImagePickerDialog() {
        val options = arrayOf("Chụp ảnh", "Chọn từ album")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Chọn ảnh")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val photoFile: File? = try {
            createImageFile()
        } catch (ex: IOException) {
            null
        }
        photoFile?.also {
            val photoURI: Uri = FileProvider.getUriForFile(
                requireContext(),
                "com.example.nonameapp.fileprovider",
                it
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        }
    }

    private fun createImageFile(): File {
        val timeStamp: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_IMAGE_PICK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == android.app.Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    val file = File(currentPhotoPath)
                    selectedImageUri = Uri.fromFile(file)
                    Glide.with(requireContext()).load(selectedImageUri).into(binding.profileImage)
                }

                REQUEST_IMAGE_PICK -> {
                    selectedImageUri = data?.data
                    Glide.with(requireContext()).load(selectedImageUri).into(binding.profileImage)
                }
            }
        }
    }

    private fun uriToFile(uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val filePath = columnIndex?.let { cursor.getString(it) }
        cursor?.close()
        return filePath?.let { File(it) }
    }

    private fun checkPassword(password: String, confirmPassword: String): Boolean {
        // Các ký tự đặc biệt (bạn có thể tùy chỉnh)
        val specialChars = "!@#$%^&*()_+{}|:"
        if (password != confirmPassword) {
            return false
        }
        val hasUppercase = password.any { it.isUpperCase() }
        val hasLowercase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { it in specialChars }

        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar && password.length >= 8


    }
}
