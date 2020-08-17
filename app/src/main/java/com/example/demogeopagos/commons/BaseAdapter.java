package com.example.demogeopagos.commons;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T, E extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<E>> {

    protected List<T> items = new ArrayList<>();
    private RecyclerView rv;
    private int viewId;

    public BaseAdapter(RecyclerView rv, int viewId) {
        this.rv = rv;
        this.viewId = viewId;
    }

    public void setData(final List<T> data) {
    if (this.items.isEmpty()) {
            this.items.addAll(data);
            notifyDataSetChanged();
      } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return items.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return compareItems(items.get(oldItemPosition), data.get(newItemPosition));
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return compareItemsContent(items.get(oldItemPosition), data.get(newItemPosition));
                }

            });
            diffResult.dispatchUpdatesTo(this);
            this.items.clear();
            this.items.addAll(data);
        }
    }

    @Override
    public BindingViewHolder<E> onCreateViewHolder(ViewGroup parent, int viewType) {

        return createViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), this.viewId, parent, false));

    }

    protected BindingViewHolder<E> createViewHolder(ViewDataBinding viewDataBinding) {
        return new BindingViewHolder<E>((E) viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<E> holder, int position) {
        populateBindViewHolder(holder, items.get(position), position);
        holder.getBinding().executePendingBindings();
    }

    protected abstract void populateBindViewHolder(BindingViewHolder<E> holder, T item, int position);

    protected abstract boolean compareItems(T itemA, T itemB);


    protected abstract boolean compareItemsContent(T itemA, T itemB);

    @Override
    public int getItemCount() {
        return items.size();
    }
}