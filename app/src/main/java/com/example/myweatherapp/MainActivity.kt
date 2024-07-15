package com.example.myweatherapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var citySpinner: Spinner
    private lateinit var getWeatherButton: Button
    private lateinit var weatherInfoTextView: TextView

    private var selectedCity: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        citySpinner = findViewById(R.id.city_spinner)
        getWeatherButton = findViewById(R.id.get_weather_button)
        weatherInfoTextView = findViewById(R.id.weather_info_text_view)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            citySpinner.adapter = adapter
        }

        citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedCity = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedCity = null
            }
        }

        getWeatherButton.setOnClickListener {
            selectedCity?.let {
                fetchWeatherData(it)
            } ?: run {
                Toast.makeText(this, "Please select a city", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchWeatherData(city: String) {
        // Mock data fetching, you can replace this with an actual API call
        val weatherData = "Weather in $city: Sunny, 25°C"
        weatherInfoTextView.text = weatherData
    }
}
