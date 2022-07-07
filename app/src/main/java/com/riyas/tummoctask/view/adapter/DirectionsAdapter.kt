package com.riyas.tummoctask.view.adapter

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.riyas.tummoctask.R
import com.riyas.tummoctask.databinding.DirectionsDesignsBinding
import com.riyas.tummoctask.model.travelapimodel.Leg
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
class DirectionsAdapter @Inject constructor() : RecyclerView.Adapter<DirectionsAdapter.PostViewHolder>() {
    private lateinit var binding: DirectionsDesignsBinding
    private lateinit var _context: Context

    /* private val itemDifferCallback = object: DiffUtil.ItemCallback<Route>(){
         override fun areItemsTheSame(oldItem: Route, newItem: Route): Boolean {
             return oldItem.summary == newItem.summary
         }

         override fun areContentsTheSame(oldItem: Route, newItem: Route): Boolean {
             return oldItem == newItem
         }
     }*/

    private val itemDifferCallback = object: DiffUtil.ItemCallback<Leg>(){
        override fun areItemsTheSame(oldItem: Leg, newItem: Leg): Boolean {
            return oldItem.distance ==newItem.distance
        }

        override fun areContentsTheSame(oldItem: Leg, newItem: Leg): Boolean {
            return oldItem ==newItem
        }

    }
    // to do job asynchronously
    private val itemDiffer = AsyncListDiffer(this, itemDifferCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        _context = parent.context
        binding = DirectionsDesignsBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = itemDiffer.currentList[position]

        try{

            //https://tutorialwing.com/create-android-horizontalscrollview-programmatically-android/
            // horizontal scroll view
            val horizontalScrollView = HorizontalScrollView(_context)
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
            horizontalScrollView.layoutParams = layoutParams

            val linearLayout = LinearLayout(_context)
            val linearParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            linearLayout.orientation = LinearLayout.HORIZONTAL
            linearLayout.layoutParams = linearParams

            horizontalScrollView.addView(linearLayout)



            currentItem.steps.forEach { dat->
                when(dat.travel_mode){
                    "WALKING" -> {
                        widgetImage(linearLayout, R.drawable.ic_walk)
                        widgetImage(linearLayout,R.drawable.ic_arrow)
                    }
                    "TRANSIT" -> {
                        widgetImage(linearLayout,R.drawable.ic_bus)
                        widgetImage(linearLayout,R.drawable.ic_arrow)
                    }
                }
            }

            val linearLayout1: LinearLayout = binding.rootContainer
            linearLayout1.addView(horizontalScrollView)
        }catch (e:Exception){

            Log.d("Exception adapter ",e.message.toString())
        }





        /*binding.tasks.text=currentItem.uName
            try{
                binding.imageView.load(currentItem.uImage) {
                    crossfade(true)
                }
            }catch (e:Exception){}*/
    }

    private fun widgetImage(linearLayout: LinearLayout, id: Int) {
        val imageView1 = ImageView(_context)
        val params1 = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params1.setMargins(30, 0, 30, 0)
        params1.gravity = Gravity.CENTER
        imageView1.layoutParams = params1
        imageView1.setImageResource(id)
        linearLayout.addView(imageView1)
    }

    override fun getItemCount(): Int = itemDiffer.currentList.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    /* fun submitList(list: List<Route>) {
         itemDiffer.submitList(list)
     }*/

    fun submitList(list: List<Leg>) {
        itemDiffer.submitList(list)
    }
}