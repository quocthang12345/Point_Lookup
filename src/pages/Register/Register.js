import "./Register.css";
import Button from "../../components/Button/Button";
import ValidateInput from "../../components/ValidateInput/ValidateInput";
import ValidateSelect from "../../components/ValidateSelect/ValidateSelect";
import { useState} from "react";
const Register = () => {
  const [registerData, setRegisterDataMain] = useState({
    address: "",
    avatar: "",
    city: "",
    fullName: "",
    passWord: "",
    phone: "",
    // "studentCode": "",
    // "teacherCode": "string",
    userName: ""
  });
  const handleOnClick = (e) => {
    e.preventDefault();
    const temp = {}
    const inputData = document.querySelectorAll(".form-control");
    inputData.forEach(data => {
        temp[data.name] = data.value;
    })
    setRegisterDataMain(temp)
  };
  return (
    <div className="register-container">
      <form action="" method="POST" className="form-register">
        <h2 className="register-form-heading">Đăng kí tài khoản</h2>
        <p className="register-form-desc">Hệ thống quản lý tra cứu điểm</p>
        <div className="spacer"></div>

        <ValidateInput
          type="text"
          name="accountName"
          rules="required"
          lable="Tên đăng nhập"
        />
        <ValidateInput
          type="text"
          name="password"
          id="password"
          rules="required"
          lable="Mật khẩu "
        />
        <ValidateInput
          type="password"
          name="confirmPassword"
          rules="required|confirm:password"
          lable="Xác nhận lại mật khẩu"
        />
        <ValidateInput
          type="text"
          name="email"
          rules="required|email"
          lable="Email"
        />
        <ValidateSelect name="major" rules="required" lable="Chuyên ngành">
          <option className="default-option" value="">
            -Chọn ngành-
          </option>
          <option value="Công nghệ thông tin">Công nghệ thông tin</option>
          <option value="Công nghệ sinh học">Công nghệ sinh học</option>
          <option value="Kiến trúc">Kiến trúc</option>
          <option value="Nhiệt điện">Nhiệt điện</option>
        </ValidateSelect>
        <div className="role-select">
          <ValidateSelect name="role" lable="Role">
            <option value="student">Học sinh</option>
            <option value="teacher">Giáo viên</option>
          </ValidateSelect>
          <ValidateInput
            type="text"
            name="codeID"
            rules="required"
            lable={"Mã"}
          />
        </div>

        <Button title="Đăng kí" onClick={handleOnClick} />
      </form>
    </div>
  );
};
export default Register;
