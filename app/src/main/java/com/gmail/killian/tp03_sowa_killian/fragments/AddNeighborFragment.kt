package com.gmail.killian.tp03_sowa_killian.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gmail.killian.tp03_sowa_killian.listeners.NavigationListener
import com.gmail.killian.tp03_sowa_killian.R
import com.gmail.killian.tp03_sowa_killian.data.NeighborRepository
import com.gmail.killian.tp03_sowa_killian.databinding.AddNeighborBinding
import com.gmail.killian.tp03_sowa_killian.helpers.validators.FieldValidators.isValidEmail
import com.gmail.killian.tp03_sowa_killian.helpers.validators.FieldValidators.isValidPhoneNumber
import com.gmail.killian.tp03_sowa_killian.helpers.validators.FieldValidators.isValidUrl
import com.gmail.killian.tp03_sowa_killian.models.Neighbor
import java.util.*


class AddNeighborFragment: Fragment() {
    private lateinit var binding: AddNeighborBinding

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddNeighborBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as? NavigationListener)?.updateTitle(R.string.new_neighbor)

        binding.saveButton.isEnabled = false
        binding.saveButton.background.alpha = 128;

        setupListeners()

        listenSaveClicked()
    }

    private fun isValidate(): Boolean =
        validateImage() && validateName() && validatePhone() && validateWebsite() && validateAddress() && validateAbout()

    private fun setupListeners() {
        binding.imageTf.addTextChangedListener(TextFieldValidation(binding.imageTf))
        binding.nameTf.addTextChangedListener(TextFieldValidation(binding.nameTf))
        binding.phoneTf.addTextChangedListener(TextFieldValidation(binding.phoneTf))
        binding.websiteTf.addTextChangedListener(TextFieldValidation(binding.websiteTf))
        binding.addressTf.addTextChangedListener(TextFieldValidation(binding.addressTf))
        binding.aboutTf.addTextChangedListener(TextFieldValidation(binding.aboutTf))
    }

    private fun listenSaveClicked() {
        binding.saveButton.setOnClickListener {
            if (isValidate()) {
                NeighborRepository.getInstance().createNeighbor(
                    Neighbor(
                        UUID.randomUUID().mostSignificantBits,
                        binding.nameTf.text.toString(),
                        binding.imageTf.text.toString(),
                        binding.addressTf.text.toString(),
                        binding.phoneTf.text.toString(),
                        binding.aboutTf.text.toString(),
                        false,
                        binding.websiteTf.text.toString()
                    )
                )
                Toast.makeText(context,
                    getString(R.string.neighbor_has_been_added), Toast.LENGTH_SHORT).show()
                fragmentManager?.popBackStack()
            }
        }
    }

    /**
     * 1) Field must not be empty
     * 2) URL must be valid
     */
    private fun validateImage(): Boolean {
        if (binding.imageTf.text.toString().trim().isEmpty()) {
            binding.imageTfWrapper.error = getString(R.string.required_field)
            binding.imageTf.requestFocus()
            return false
        } else if ( !isValidUrl(binding.imageTf.text.toString())) {
            binding.imageTfWrapper.error =  getString(R.string.invalid_url)
            binding.imageTf.requestFocus()
            return false
        } else {
            print("image is valid")
            binding.imageTfWrapper.isErrorEnabled = false
            val context = binding.root.context
            Glide.with(context)
                .load(binding.imageTf.text.toString())
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .skipMemoryCache(false)
                .into(binding.profileImageView)
        }
        return true
    }

    /**
     * Field must not be empy
     */
    private fun validateName(): Boolean {
        if (binding.nameTf.text.toString().trim().isEmpty()) {
            binding.nameTfWrapper.error = getString(R.string.required_field)
            binding.nameTf.requestFocus()
            return false
        } else {
            binding.nameTfWrapper.isErrorEnabled = false
        }
        return true
    }

    /**
     * Field must not be empty
     */
    private fun validatePhone(): Boolean {
        if (binding.phoneTf.text.toString().trim().isEmpty()) {
            binding.phoneTfWrapper.error = getString(R.string.required_field)
            binding.phoneTf.requestFocus()
            return false
        } else if ( !isValidPhoneNumber(binding.phoneTf.text.toString())) {
            binding.phoneTfWrapper.error =  getString(R.string.invalid_phone_number)
            binding.phoneTf.requestFocus()
            return false
        } else {
            binding.phoneTfWrapper.isErrorEnabled = false
        }
        return true
    }

    /**
     * 1) Field must not be empty
     * 2) URL must be valid
     */
    private fun validateWebsite(): Boolean {
        if (binding.websiteTf.text.toString().trim().isEmpty()) {
            binding.websiteTfWrapper.error = getString(R.string.required_field)
            binding.websiteTf.requestFocus()
            return false
        } else if ( !isValidUrl(binding.websiteTf.text.toString())) {
            binding.websiteTfWrapper.error = getString(R.string.invalid_url)
            binding.websiteTf.requestFocus()
            return false
        } else {
            binding.websiteTfWrapper.isErrorEnabled = false
        }
        return true
    }

    /**
     * 1) field must not be empty
     * 2) text should matches email address format
     */
    private fun validateAddress(): Boolean {
        if (binding.addressTf.text.toString().trim().isEmpty()) {
            binding.addressTfWrapper.error = getString(R.string.required_field)
            binding.addressTf.requestFocus()
            return false
        } else if (!isValidEmail(binding.addressTf.text.toString())) {
            binding.addressTfWrapper.error =  getString(R.string.invalid_email)
            binding.addressTf.requestFocus()
            return false
        } else {
            binding.addressTfWrapper.isErrorEnabled = false
        }
        return true
    }

    /**
     * 1) Field must not be empty
     * 1) Max characters authorized is 30
     */
    private fun validateAbout(): Boolean {
        println(binding.aboutTf.text.toString().count())
        if (binding.aboutTf.text.toString().trim().isEmpty()) {
            binding.aboutTfWrapper.error = getString(R.string.required_field)
            binding.aboutTf.requestFocus()
            return false
        } else if (binding.aboutTf.text.toString().count() > 30) {
            binding.aboutTfWrapper.error =  getString(R.string.maximum_30_characters)
            binding.aboutTf.requestFocus()
            return false
        } else {
            binding.aboutTfWrapper.isErrorEnabled = false
        }
        return true
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // checking ids of each text field and applying functions accordingly.
            binding.saveButton.isEnabled = isValidate()

            if (isValidate()) {
                binding.saveButton.background.alpha = 255;
            } else {
                binding.saveButton.background.alpha = 128;
            }

            when (view.id) {
                R.id.imageTf -> {
                    validateImage()
                }
                R.id.nameTf -> {
                    validateName()
                }
                R.id.phoneTf -> {
                    validatePhone()
                }

                R.id.websiteTf -> {
                    validateWebsite()
                }

                R.id.addressTf -> {
                    validateAddress()
                }

                R.id.aboutTf -> {
                    validateAddress()
                }
            }
        }
    }
}