package ssd.lovetolearn.toolbox.activity;
import android.content.Context;
import android.support.v7.appcompat.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Map;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Myholder>
{

   private Context context;
   private List<Map<String,String>> mdata;
   

   public RecyclerAdapter(Context context,List<Map<String,String>> mdata){
      this. context=context;
       this.mdata=mdata;
       
   }
   
   
    @Override
    public RecyclerAdapter.Myholder onCreateViewHolder(ViewGroup p1, int p2) {
        View view=LayoutInflater.from(context).inflate(R.layout.recycler_item,null);
        Myholder myholder=new Myholder(view);
        return myholder;
    }

    @Override
    public void onBindViewHolder(final Myholder holder, int position) {
        holder.textView.setText(mdata.get(position).get("text"));
       
        Glide.with(context)
            .load(mdata.get(position).get("image"))
           // .placeholder(R.drawable.image_1)
            .into(holder.imageView);  
      //  Glide.with(context).load("http://goo.gl/gEgYUd").into(holder.imageView);
        
       // holder.imageView.setBackgroundResource(mdata.get(position).get("image"));
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
    
    
    
    
    
    
   static class Myholder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public Myholder(View itemView){
            super(itemView);  
            textView=itemView.findViewById(R.id.recycleritemTextView1);
            imageView=itemView.findViewById(R.id.recycler_itemImageView);
        }
        
        
    }
    
    
    
    
    
}
