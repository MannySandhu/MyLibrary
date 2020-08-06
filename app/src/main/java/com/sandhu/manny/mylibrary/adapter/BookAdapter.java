package com.sandhu.manny.mylibrary.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandhu.manny.mylibrary.R;
import com.sandhu.manny.mylibrary.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private List<Book> books = new ArrayList<>();

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item, parent, false);
        return new BookHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        Book currentBook = books.get(position);
        holder.title.setText(currentBook.getTitle());
        holder.author.setText(currentBook.getAuthor());
        holder.genre.setText(currentBook.getGenre());
        holder.pages.setText(currentBook.getPages());
        holder.published.setText(currentBook.getPublished());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    class BookHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView author;
        private TextView genre;
        private TextView pages;
        private TextView published;

        public BookHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            author = itemView.findViewById(R.id.text_view_author);
            genre = itemView.findViewById(R.id.text_view_genre);
            pages = itemView.findViewById(R.id.text_view_pages);
            published = itemView.findViewById(R.id.text_view_published);
        }
    }
}
