package com.example.practico3tindermoviles1fabianeduardogilcespedes.Fragmement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.practico3tindermoviles1fabianeduardogilcespedes.R
import com.example.practico3tindermoviles1fabianeduardogilcespedes.Adapters.ImagePagerAdapter
import com.example.practico3tindermoviles1fabianeduardogilcespedes.databinding.FragmentMainBinding
import com.example.practico3tindermoviles1fabianeduardogilcespedes.ViewModel.MainViewModel
import com.example.practico3tindermoviles1fabianeduardogilcespedes.Persona

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        // Observer
            viewModel.persona.observe(viewLifecycleOwner) { persona ->
            if (persona != null) {
                binding.nombrePersona.text = persona.nombre
                binding.imageViewPager.adapter = ImagePagerAdapter(persona.imagen)
                configurarIndicadores(persona)
                binding.imageViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        actualizarIndicadores(position)
                    }
                })
            } else {
                binding.nombrePersona.text = " "
                binding.indicadorLayout.removeAllViews()
                binding.imageViewPager.adapter = ImagePagerAdapter(listOf(R.drawable.t1))
            }
        }

        binding.btnMeGusta.setOnClickListener {
            viewModel.likePerson()
        }

        binding.btnNoMegusta.setOnClickListener {
            viewModel.dislikePerson()
        }

        binding.btnMisLikes.setOnClickListener {
            val likedPeople = ArrayList(viewModel.getLikedPeople())
            val fragment = LikesFragment.newInstance(likedPeople)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }

    private fun configurarIndicadores(persona: Persona) {
        // Limpiar indicadores
        binding.indicadorLayout.removeAllViews()

        val indicators = arrayOfNulls<TextView>(persona.imagen.size)
        for (i in indicators.indices) {
            indicators[i] = TextView(context).apply {
                text = "▫️"
                textSize = 24f
            }
            binding.indicadorLayout.addView(indicators[i])
        }
        actualizarIndicadores(0)
    }


    private fun actualizarIndicadores(position: Int) {
        for (i in 0 until binding.indicadorLayout.childCount) {
            val textView = binding.indicadorLayout.getChildAt(i) as TextView
            textView.text = if (i == position) "◻️" else "▫️"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}