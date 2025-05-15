# Vocabit 📚✨  
**Vocabit** là một ứng dụng học từ vựng tiếng Anh trên nền tảng Android, giúp người học ghi nhớ từ vựng hiệu quả qua các chế độ luyện tập đa dạng, kèm theo chế độ thi và bảng xếp hạng để tăng tính cạnh tranh.

## 🌟 Tính năng nổi bật

- 🔤 **Practice Mode** với nhiều dạng câu hỏi:
  - **Image to Text**: Đoán từ dựa trên hình ảnh.
  - **Fill in the Blank**: Điền từ vào chỗ trống trong câu.
  - **Extra Letter**: Loại bỏ chữ cái thừa để hoàn thành từ đúng.
  - **Match**: Nối từ với nghĩa hoặc hình ảnh phù hợp.

- 📝 **Exam Mode**:
  - Tổng hợp 4 loại câu hỏi ngẫu nhiên.
  - Tính thời gian, chấm điểm, lưu kết quả vào cơ sở dữ liệu.
  
- 🏆 **Bảng xếp hạng**:
  - Xếp hạng người chơi theo điểm thi cao nhất.
  
- 👤 **Quản lý người dùng**:
  - Đăng ký, đăng nhập, cập nhật thông tin cá nhân.
  - Ảnh đại diện được lưu trữ trên **Cloudinary**.

## 🛠️ Công nghệ sử dụng

- **Android (MVVM Architecture)**
- **Room Database** – lưu trữ cục bộ
- **MySQL + PHP API** – lưu trữ dữ liệu người dùng và điểm số
- **Retrofit + RxJava** – gọi API và xử lý bất đồng bộ
- **Cloudinary** – lưu ảnh đại diện người dùng
- **Data Binding** – kết nối giao diện và ViewModel

## 🚀 Cài đặt & chạy thử

1. **Clone repo:**
   ```bash
   git clone https://github.com/ThuyLinh09/Vocabit.git

2. **Chạy sql:**
   ```Mở file Vocabit.sql chạy lệnh tạo database sau đó insert các giá trị vào bảng


