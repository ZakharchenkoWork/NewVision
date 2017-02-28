/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.znshadows.newvision.screens.subscribers;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.znshadows.newvision.R;
import com.znshadows.newvision.models.SubscriberData;

import java.util.ArrayList;
import java.util.List;


public class SubscribersAdapterTest extends RecyclerView.Adapter<SubscribersAdapterTest.DataViewHolder> {

    private static final String TAG = SubscribersAdapter.class.getSimpleName();
    private List<SubscriberData> items = new ArrayList<>();

    public SubscribersAdapterTest(List<SubscriberData> items) {

        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
    }


    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subscriber_item, viewGroup, false);
        return new DataViewHolder(view);


    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(items.get(position));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView name;


        public DataViewHolder(View itemView) {
            super(itemView);
            avatar = (ImageView) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);

        }

        void bind(SubscriberData item) {
            name.setText(item.getLogin());
           ImageLoader.getInstance().displayImage(item.getAvatarUrl(), avatar); // Default options will be used

        }


    }
}
