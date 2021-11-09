import React from "react";
import "./LoginCss.css"

const RegisterComponent = () => {

    return(
        <div>
            <div class="main" style={{display: "block", padding: "24px"}}>
                <form action="" method="POST" class="form" id="form-register" style={{border: "1px solid #111", margin: 0, marginLeft:"20%"}}>
                    <h1 class="heading">Đăng kí</h1>
                    <p class="desc" style={{fontSize: "x-large", fontWeight: "bold"}}>Hệ thống quản lý tra cứu điểm</p>
            
                    <div class="spacer"></div>
            
                    <div class="form-group">
                        <label for="fullname" class="form-label">Tên đăng nhập</label>
                        <input id="fullname" name="fullname" type="text" rules = "required" placeholder="Nhập tên đăng nhập" class="form-control" />
                        <span class="form-message"></span>
                    </div>
                
                    <div class="form-group">
                        <label for="password" class="form-label">Mật khẩu</label>
                        <input id="password" name="password" type="password" rules = "required" placeholder="Nhập mật khẩu" class="form-control" />
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="email" class="form-label">Email</label>
                        <input id="email" name="email" type="text" rules = "required|email" placeholder="Nhập địa chỉ" class="form-control" />
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="phone" class="form-label">Số điện thoại</label>
                        <input id="phone" name="phone" type="number" rules = "required" placeholder="Nhập địa chỉ" class="form-control" />
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="city" class="form-label">Thành phố</label>
                        <input id="city" name="city" type="text" rules = "required" placeholder="Nhập thành phố" class="form-control" />
                        <span class="form-message"></span>
                    </div>


                    <div class="form-group">
                        <label for="address" class="form-label">Địa chỉ</label>
                        <input id="address" name="address" type="text" rules = "required" placeholder="Nhập địa chỉ" class="form-control" />
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="major" class="form-label">Ngành</label>
                        <select class="form-control" id="major" name="major" placeholder="-Chọn ngành-" >
                            <option value="">-Chọn ngành-</option>
                            <option value="Công nghệ thông tin">Công nghệ thông tin</option>
                            <option value="Công nghệ sinh học">Công nghệ sinh học</option>
                            <option value="Kiến trúc">Kiến trúc</option>
                            <option value="Nhiệt điện">Nhiệt điện</option>
                        </select>
                        <span class="form-message"></span>
                    </div>

                    <div class="form-group">
                        <label for="class" class="form-label">Ngành</label>
                        <select class="form-control" id="class" name="class" placeholder="-Chọn ngành-" >
                            <option value="">-Chọn lớp-</option>
                            <option value="1">Lớp 1</option>
                            <option value="2c">Lớp 2</option>
                            <option value="3">Lớp 3</option>
                            <option value="4">Lớp 4</option>
                        </select>
                        <span class="form-message"></span>
                    </div>
                
                    
                    <button class="form-submit" style={{fontSize :"large"}}>Đăng kí</button>
                </form>
             
            </div>
        </div>
    )
}
export default RegisterComponent;