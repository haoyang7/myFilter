package com.zj.myfilter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;


/**
 * Created by ZhengJiao on 2017/4/24.
 */
public class FlowPopListViewAdapter extends BaseAdapter {

    private Activity context;
    private List<FiltrateBean> dictList;

    public FlowPopListViewAdapter(Activity context, List<FiltrateBean> dictList) {
        this.context = context;
        this.dictList = dictList;
    }

    @Override
    public int getCount() {
        return dictList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_property, null);
            vh = new ViewHolder();
            vh.tvTypeName = (TextView) convertView.findViewById(R.id.tv_type_name);
            vh.layoutProperty = (SkuFlowLayout) convertView.findViewById(R.id.layout_property);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        FiltrateBean filtrateBean = dictList.get(position);
        vh.tvTypeName.setText(filtrateBean.getTypeName());

        setFlowLayoutData(filtrateBean.getChildren(), vh.layoutProperty);

        return convertView;
    }

    private void setFlowLayoutData(final List<FiltrateBean.Children> childrenList, final SkuFlowLayout flowLayout) {

        flowLayout.removeAllViews();
        for (int x = 0; x < childrenList.size(); x++) {
            CheckBox checkBox = (CheckBox) View.inflate(context, R.layout.item_flowlayout_bill, null);
            checkBox.setText(childrenList.get(x).getValue());

            if (childrenList.get(x).isSelected()) {
                checkBox.setChecked(true);
            }

            final int finalX = x;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    refreshCheckBox(flowLayout, finalX, childrenList);
                }
            });
            flowLayout.addView(checkBox);

        }

    }

    private void refreshCheckBox(SkuFlowLayout flowLayout, int finalX, List<FiltrateBean.Children> propBeenList) {
        CheckBox radio = (CheckBox) flowLayout.getChildAt(finalX);
        propBeenList.get(finalX).setSelected(radio.isChecked());
    }

    class ViewHolder {
        private TextView tvTypeName;
        private SkuFlowLayout layoutProperty;
    }
}
