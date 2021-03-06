package lk.dev.exerciseso

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import lk.dev.exerciseso.Adaptors.PostAdaptor
import lk.dev.exerciseso.posts.api.PostClient
import lk.dev.exerciseso.posts.models.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    var postList =ArrayList<Post>()


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

        val view: View = inflater.inflate(R.layout.fragment_third, container, false)
        val activity = activity as Context
        val recyclerView=view.findViewById<RecyclerView>(R.id.post_data)
        recyclerView.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        getData()
        recyclerView.adapter=PostAdaptor(this,postList)




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
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        val gson= GsonBuilder().create()
//        // Inflate the layout for this fragment
//        val retrofit = Retrofit.Builder()
//                .baseUrl("https://jsonplaceholder.typicode.com")
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build()
//
//        var postApi: PostApi=retrofit.create(PostApi::class.java)
//        var postCall = postApi.posts
//        postCall.enqueue(object : Callback<List<Post>>{
//            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
//                val listView= view?.findViewById<RecyclerView>(R.id.post_data)
//                val adaptor=PostAdaptor(postList)
//                listView?.adapter =adaptor
//                println("connected")
//            }
//
//            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
//                Log.d("GET ApI",t.message.toString())
//            }
//
//        })
    }
    private fun getData(){
        val call: Call<ArrayList<Post>> = PostClient.getPostClient.getPosts()
        call.enqueue(object : Callback<ArrayList<Post>>{
            override fun onResponse(call: Call<ArrayList<Post>>, response: Response<ArrayList<Post>>) {
                postList.addAll((response.body()!!))
                view?.findViewById<RecyclerView>(R.id.post_data)?.adapter?.notifyDataSetChanged()
                println("Success")
            }

            override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                println("Process Failed")
                if (t != null) {
                    Log.d("GET api",t.message.toString())
                }
            }

        })
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