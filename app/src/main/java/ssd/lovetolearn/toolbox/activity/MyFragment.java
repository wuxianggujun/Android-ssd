package ssd.lovetolearn.toolbox.activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.appcompat.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ssd.lovetolearn.toolbox.utils.adapter.RecyclerViewItemDecoration;
import ssd.lovetolearn.toolbox.MainActivity;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.io.IOException;

public class MyFragment extends Fragment
{ 
   
    private RecyclerView recyclerView;
    private List<Map<String,String>> mdata=new ArrayList<Map<String,String>>();

    
	private boolean isFirst = true;//加个Boolean值，new就true，如果是false就加载数据
   
   @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
      
        shuju();     
        initUIAndData();
       return view;
   }

  
   private void initUIAndData() {
       RecyclerAdapter adapter=new RecyclerAdapter(getActivity().getApplicationContext(),mdata);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       recyclerView.setAdapter(adapter);
       
       RecyclerViewItemDecoration  recyclerViewItemDecoration = new RecyclerViewItemDecoration(getActivity().getApplicationContext());
       recyclerView.addItemDecoration(recyclerViewItemDecoration);
       
       
   }
   
   
   private void shuju(){
      
       
       new Thread(new Runnable(){

               @Override

               public void run(){

                   //线程里要执行的东西写这
                   try {
                       Document document=Jsoup.connect("https://www.ziyuanba.com/list/1/").get();
                       Elements elements=document.select("ul.list-soft").select("li.layui-clear");

                       for(Element element:elements){

                           Map<String,String> item1=new HashMap<String,String>();
                           item1.put("text",element.select("div.list-info").select("a.soft-title").text());
                           item1.put("image",element.select("a").select("img").attr("src"));
                           mdata.add(item1);
                       }

                   } catch (IOException e) {
                       e.printStackTrace();

                   }
                   
                   
               }

           }).start();
            
          
   }
    //    ，下面方法意思是页面可见时的事件，isfirst代表第一次打开这个fragment。
    
   @Override
   public void setUserVisibleHint(boolean isVisibleToUser) {
	   // TODO: Implement this method
	   super.setUserVisibleHint(isVisibleToUser);
	   if(isVisibleToUser && isFirst) {
		   shuju();
		   isFirst = false;
	   }
   }


   }
   
   
