package com.ul.resads.ui;

import java.util.List;

import com.ul.resads.data.ListPromotion;
import com.ul.resads.data.Promotion;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PromotionFragment extends Fragment {
	private ListView listPromotion;
	private LayoutInflater inflater;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_promotion, container,false);
		listPromotion=(ListView) view.findViewById(R.id.listViewPromotion);
		this.inflater=inflater;
		new LoadPromotionTask().execute("http://phone-price.info/res_ads/getdata.php?action=pro");
		return view;
	}
	private class LoadPromotionTask extends AsyncTask<String, Integer, List<Promotion>>{

		@Override
		protected List<Promotion> doInBackground(String... params) {
			ListPromotion lp=new ListPromotion();
			lp.excuteURL(params[0]);
			
			return lp.getListPromotion();
		}
		protected void onPostExecute(List<Promotion> result){
			ArrayAdapter<Promotion> adapter=new PromotionListAdapter(getActivity().getApplicationContext(),
							R.layout.list_promotion_item, result);
			listPromotion.setAdapter(adapter);
			
		}
		
		
	}
	
	private class PromotionListAdapter extends ArrayAdapter<Promotion>{
		private int resourceid;
		private List<Promotion> list;
		public PromotionListAdapter(Context context, int textViewResourceId,
				List<Promotion> objects) {
			super(context, textViewResourceId, objects);
			this.resourceid=textViewResourceId;
			this.list=objects;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			if (convertView == null) {
				view = inflater.inflate(resourceid, null);
				final ViewHolder vholder = new ViewHolder();
				vholder.textViewPromoteInfo = (TextView) view.findViewById(R.id.textViewPromoteInfo);
				vholder.textViewName=(TextView)view.findViewById(R.id.textViewResName);
				vholder.textViewAddress=(TextView)view.findViewById(R.id.textViewAddress);
				view.setTag(vholder);

			} else {
				view = convertView;
			
			}

			ViewHolder holder = (ViewHolder) view.getTag();
			holder.textViewPromoteInfo.setText(list.get(position).getDes());
			holder.textViewName.setText(list.get(position).getResName());
			holder.textViewAddress.setText(list.get(position).getAddress());
			return view;
		}
		
	}
	
	static class ViewHolder {
		protected TextView textViewPromoteInfo;
		protected TextView textViewName;
		protected TextView textViewAddress;

	}
	
	
	
	
}
