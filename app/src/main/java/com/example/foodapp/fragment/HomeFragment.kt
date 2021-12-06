package com.example.foodapp.fragment

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.foodapp.R
import com.example.foodapp.adapter.HomeRecylerAdapter
import com.example.foodapp.database.ResDatabase
import com.example.foodapp.database.ResEntity
import com.example.foodapp.model.Restaurants


class HomeFragment : Fragment() {

    lateinit var recyclerHome: RecyclerView

    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerAdapter: HomeRecylerAdapter

    val restaurantInfoList = arrayListOf<Restaurants>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerHome = view.findViewById(R.id.recyclerHome)

        layoutManager = LinearLayoutManager(activity)

        val queue = Volley.newRequestQueue(activity as Context)

        val url = "http://13.235.250.119/v2/restaurants/fetch_result/"

        val jsonObjectRequest = object :JsonObjectRequest(Method.GET, url, null,
            Response.Listener {

                val data = it.getJSONObject("data")
                val success = data.getBoolean("success")

                if (success) {
                    val resArray = data.getJSONArray("data")
                    for (i in 0 until resArray.length()) {
                        val resObject = resArray.getJSONObject(i)
                        val restaurant = Restaurants(
                            resObject.getString("id"),
                            resObject.getString("name"),
                            resObject.getString("rating"),
                            resObject.getString("cost_for_one"),
                            resObject.getString("image_url")
                        )



                        val checkFav = DBAsyncTak(activity as Context, ResEntity, 1).execute()

                        restaurantInfoList.add(restaurant)

                        recyclerAdapter = HomeRecylerAdapter(activity as Context, restaurantInfoList)

                        recyclerHome.adapter = recyclerAdapter

                        recyclerHome.layoutManager = layoutManager
                    }
                } else {
                    Toast.makeText(activity as  Context, "Some Error Occurred!!!", Toast.LENGTH_SHORT).show()
                }

        }, Response.ErrorListener {
                Toast.makeText(activity as  Context, "Volley Error Occurred!!!", Toast.LENGTH_SHORT).show()
        }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "2ad0fbd582451f"
                return headers
            }
        }

        queue.add(jsonObjectRequest)

        return view
    }

    class DBAsyncTak(val context: Context, val resEntity: ResEntity, val mode: Int): AsyncTask<Void, Void, Boolean>() {

        val db = Room.databaseBuilder(context, ResDatabase::class.java, "res-db").build()

        override fun doInBackground(vararg params: Void?): Boolean {

            when(mode) {
                1 -> {
                    //Check DB if restaurant is fav or not
                    val res: ResEntity? = db.ResDao()
                }

            }
            return false;
        }
    }
}
