package vn.com.example.demorealm.custom.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import vn.com.example.demorealm.R;
import vn.com.example.demorealm.model.callback.StudentsCallback;
import vn.com.example.demorealm.model.data.Student;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private StudentsCallback studentsCallback;
    private List<Student> students;

    public StudentAdapter(Context context, StudentsCallback studentsCallback, List<Student> students) {
        this.context = context;
        this.studentsCallback = studentsCallback;
        this.students = students;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new StudentViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.custom_item_student,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int position) {
        studentViewHolder.bindData(students.get(position));
    }

    @Override
    public int getItemCount() {
        return students == null ? 0 : students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvIdStudent;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdStudent = itemView.findViewById(R.id.tv_id_student);
            tvName = itemView.findViewById(R.id.tv_name);
            setEvent(itemView);
        }

        private void setEvent(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    studentsCallback.onClickStudentItem(students.get(getAdapterPosition()));
                }
            });
        }

        public void bindData(Student student) {
            tvName.setText(student.getName());
            tvIdStudent.setText(student.getStudentId());
        }
    }

}
