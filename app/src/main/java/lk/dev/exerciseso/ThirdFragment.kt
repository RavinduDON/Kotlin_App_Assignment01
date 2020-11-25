package lk.dev.exerciseso

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import lk.dev.exerciseso.Adaptors.PostAdaptor
import lk.dev.exerciseso.posts.api.PostApi
import lk.dev.exerciseso.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThirdFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThirdFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val gson= GsonBuilder().create()
        // Inflate the layout for this fragment
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        var postApi: PostApi=retrofit.create(PostApi::class.java)
        var postCall = postApi.posts
        postCall.enqueue(object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val listView= view?.findViewById<RecyclerView>(R.id.post_data)
                val adaptor=PostAdaptor(response.body() as ArrayList<Post>)
                listView!!.adapter=adaptor
                println("data recived")
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
               Log.d("GET ApI",t.message.toString())
            }

        })
//        var postCall=postApi.posts
//        postCall.enqueue(object : Callback<Post>{
//            override fun onResponse(call: Call<Post>, response: Response<Post>) {
//                var title = response.body()!!.title
//                Log.d("ThirdFragment","This is "+title)
//                view!!.findViewById<TextView>(R.id.title_textview).text=title
//
//            }
//
//            override fun onFailure(call: Call<Post>, t: Throwable) {
//                Log.d("ThirdFragment","Error with API")
//            }
//
//        })
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThirdFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThirdFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}