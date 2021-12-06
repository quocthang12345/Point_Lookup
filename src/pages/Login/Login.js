import "./Login.css";
import Button from "../../components/Button/Button";
import ValidateInput from "../../components/ValidateInput/ValidateInput";
import { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import axios from "axios";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  useLocation,
  Link,
} from "react-router-dom";
const Login = () => {
  const history = useHistory();
  const handleSubmitLogin = (e) => {
    e.preventDefault();
    const loginInputs = document.querySelectorAll(".form-control");
    // console.log({
    //   username: loginInputs[0].value,
    //   password: loginInputs[1].value,
    // })
    axios
      .post("http://localhost:8080/api/login", {
        username: loginInputs[0].value,
        password: loginInputs[1].value,
      })
      .then((response) => {
        localStorage.setItem("token", response.data);
        history.push("/adminprofile");
      })
      .catch((error) => {
        console.log(error);
        alert("Đăng nhập thất bại")
      });
  };

  return (
    <div className="login-page">
      <div className="deco-login">
        <div>
          <h1>{"TRANG ĐĂNG NHẬP"}</h1>
        </div>
        <div className="spacer"></div>
        <div style={{ marginBottom: "50px" }}>
          <h3>{"Hệ thống tra cứu & quản lý điểm"}</h3>
        </div>
        <Link to="/" class="back-to-home">
          Quay lại trang chủ
        </Link>
      </div>
      <div className="login-container">
        <form onSubmit={handleSubmitLogin} method="POST" className="form-login">
          <h2 className="login-form-heading">Đăng nhập</h2>
          <p className="login-form-desc">Hệ thống quản lý tra cứu điểm</p>
          <div className="spacer"></div>

          <ValidateInput
            type="text"
            name="currentPassword"
            rules="required"
            lable="Tên đăng nhập"
          />
          <ValidateInput
            type="password"
            name="currentPassword"
            rules="required"
            lable="Mật khẩu"
          />

          <Button title="Đăng nhập" onClick={handleSubmitLogin} />
          <Link to="/register" className="register-link">
            {" "}
            Đăng kí tại đây
          </Link>
        </form>
      </div>
    </div>
  );
};
export default Login;
