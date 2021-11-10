import "./ManageStudentAccount.css"
import "../ManageTeacherAccount/ManageTeacherAccount.css"
import Table from 'react-bootstrap/Table';
import ValidateSelect from "../../../components/ValidateSelect/ValidateSelect"
import ValidateInput from "../../../components/ValidateInput/ValidateInput"
import Button from "../../../components/Button/Button"
import Header from "../../../components/Header/Header"
import {useState} from 'react'
import { Link } from "react-router-dom";
const ManageStudentAccount = () => {
    return (
        <>
            <Header name = "admin Thịnh" isLoggedIn ={true}>
                <div className = "nav-item-header"><b>Cá nhân</b>
                    <div className = "dropdown-content">
                        <Link to ="/adminprofile">Thông tin cá nhân</Link>
                        <Link to ="/changepassword">Đổi mật khẩu</Link>
                    </div>
                </div>
                <div className = "nav-item-header"><b>Quản lí tài khoản</b>
                    <div className = "dropdown-content">
                        <Link to ="/manageteacheraccount">Tài khoản giáo viên</Link>
                        <Link>Tài khoản học sinh</Link>
                    
                        
                    </div>
                </div>
                <div className = "nav-item-header"><b>Quản lí học tập</b>
                    <div className = "dropdown-content">
                        <Link to = "/managemajor">Quản lí lớp, khoa</Link>
                        <Link >Quản lí điểm</Link>
                    </div>
                </div>
            </Header>
            <div className = "manage-account-page" style ={{ marginTop: "20px" }}>
                <h2 style = {{margin: " 10px 20px", borderBottom: "solid 1px #111"}}>Trang quản lí tài khoản học sinh</h2>
                <div className = "search-container">
                    <ValidateSelect name ="major" lable = "Ngành">
                        <option className = "default-option" value="">-Chọn ngành-</option>
                        <option value="Công nghệ thông tin">Công nghệ thông tin</option>
                        <option value="Công nghệ sinh học">Công nghệ sinh học</option>
                        <option value="Kiến trúc">Kiến trúc</option>
                        <option value="Nhiệt điện">Nhiệt điện</option>
                    </ValidateSelect>
                    <ValidateSelect name ="class" lable = "Lớp">
                        <option className = "default-option" value="">-Chọn lớp-</option>
                        <option value="Công nghệ thông tin">Công nghệ thông tin</option>
                        <option value="Công nghệ sinh học">Công nghệ sinh học</option>
                        <option value="Kiến trúc">Kiến trúc</option>
                        <option value="Nhiệt điện">Nhiệt điện</option>
                    </ValidateSelect>
                    <ValidateInput name ="teacher-name" lable = "Tên"/>
                    <Button title="Dữ liệu"/>
                </div>
                <div className ="manage-account">
                    <h5 >Quản lý tài khoản Học sinh</h5>
                    <Table responsive bordered hover>
                        <thead >
                            <tr>
                                <th >ID</th>
                                <th >Tên học sinh</th>
                                <th >Tên đăng nhập</th>
                                <th >Mật khẩu</th>
                                <th>Chi tiết</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th >1/2021</th>
                                <td>6</td>
                                <td>9</td>
                                <td>9</td>
                                <td>
                                    <a href="$">Chi tiết</a>
                                </td>
                            </tr>
                            <tr>
                                <th >2/2021</th>
                                <td>5</td>
                                <td>10</td>
                                <td>9.8</td>
                                <td>
                                    <a href="$">Chi tiết</a>
                                </td>
                            </tr>
                            <tr>
                                <th >3/2021</th>
                                <td >10</td>
                                <td>8</td>
                                <td>8.7</td>
                                <td>
                                    <a href="$">Chi tiết</a>
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </div>
            </div>
        </>
    )
}
export default ManageStudentAccount 