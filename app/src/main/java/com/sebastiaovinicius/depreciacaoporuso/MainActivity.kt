package com.sebastiaovinicius.depreciacaoporuso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sebastiaovinicius.depreciacaoporuso.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculateTaxa.setOnClickListener(this)
        binding.buttonCalculateDepreciacao.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        if(v.id==R.id.button_calculateTaxa){

            if(isValid()){
                binding.textViewTaxaDepreciacao.text=calculaTaxaDepreciacao().toString()
            }

        }
        if(v.id==R.id.button_calculateDepreciacao){
           if (isValid()){

               var depre=calculaDepreciacao(calculaTaxaDepreciacao())

               binding.textViewDepreciacaoPorUso.text="R$ ${"%.2f".format(depre)}"
           }
        }


    }
    private fun calculaTaxaDepreciacao():Double{

            var taxaDepreciacao:Double
            var horasTrabalhadas = binding.editTextNHoraTrabNoPeriodo.text.toString().toDouble()
            var horasEstimadas   = binding.editTextNHorasEstimadasVidaUtil.text.toString().toDouble()

             taxaDepreciacao= horasTrabalhadas/horasEstimadas

        return taxaDepreciacao
    }
    private fun calculaDepreciacao(taxaDepre:Double):Double{
        var custoNovo= binding.editTextCustoNovo.text.toString().toDouble()
        var depreciacao= custoNovo*taxaDepre
        return depreciacao
    }

    private fun isValid():Boolean{
        return binding.editTextNHoraTrabNoPeriodo.text.toString()!=""&&
                binding.editTextNHorasEstimadasVidaUtil.text.toString()!=""&&
                binding.editTextCustoNovo.text.toString()!=""
    }
}