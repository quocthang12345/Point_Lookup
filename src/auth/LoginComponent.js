import React from "react";
import "./LoginCss.css"
import { Link } from "react-router-dom";

const LoginComponent = () => {

    return(
        <div style={{display : "flex"}}>
            <div class="deco">
                <div>
                    <h1>TRANG ĐĂNG NHẬP</h1>
                </div>
                <div class="spacer"></div>
                <div>
                    <h3>Hệ thống tra cứu & quản lý điểm</h3>
                </div>
            </div>
            <div class="main">
                <form action="" method="POST" class="form" id="form-register">
                    <h2 class="heading">Đăng nhập</h2>
                    <p class="desc">Hệ thống quản lý tra cứu điểm</p>
            
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
                
                    
                    <Link to="/profile"><button class="form-submit">Đăng nhập</button></Link>
                    <Link to="/register"><a class = "register"> Đăng kí tại đây</a></Link>
                </form>
                
                
            </div>
        </div>
    )
}

export default LoginComponent;