import "./ScoreTeacher.css"
import "../../Student/ScoreStudent/ScoreStudent.css"
import ValidateSelect from "../../../components/ValidateSelect/ValidateSelect"
import Button from "../../../components/Button/Button"
import Table from 'react-bootstrap/Table'
import Modal from 'react-bootstrap/Modal'
import Header from "../../../components/Header/Header"
import {useState} from 'react'
import { Link } from "react-router-dom";

const ScoreTeacher = (props) => {
    const [info, setInfo] = useState({})
    const [lgShow, setLgShow] = useState(false);
    const handleShowModal = (e) =>{
        const selectedRowInfo = e.target.closest("tr").querySelectorAll("td")
        setInfo(prev => ({
            studentName: selectedRowInfo[0].innerText,
            BT: selectedRowInfo[1].innerText,
            GK: selectedRowInfo[2].innerText,
            CK: selectedRowInfo[3].innerText,
        }))
        setLgShow(true)
    }
    return (
        <div className="score-teacher-page">
            <Header isLoggedIn = {true} name="tên giáo viên">
            <div className = "nav-item-header"><b>Cá nhân</b>
                <div className = "dropdown-content">
                    <Link to ="/teacherprofile">Thông tin cá nhân</Link>
                    <Link to ="/changepassword">Đổi mật khẩu</Link>
                </div>
            </div>
            <div className = "nav-item-header"><b>Quản lí điểm</b>
                <div className = "dropdown-content">
                    <Link >Yêu cầu nhà trường</Link>
                    <Link >Quản lí điểm học sinh</Link>
                    
                </div>
            </div>
            <div className = "nav-item-header"><b>Khác</b>
                <div className = "dropdown-content">
                    <a href="#">Dropdown 1</a>
                    <a href="#">Dropdown 2</a>
                    <a href="#">Dropdown 3</a>
                </div>
            </div>
            </Header>
            <h2 className ="score-page-title">Kết quả học tập</h2>
            <Modal size="lg" show={lgShow} onHide={() => setLgShow(false)} aria-labelledby="example-modal-sizes-title-lg">
                <Modal.Header closeButton>
                    <Modal.Title id="example-modal-sizes-title-lg">{info.studentName}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Table className = "detail-score-table" responsive bordered hover>
                        <thead >
                            <tr>
                                <th>Bài tập</th>
                                <th>Giữa kì</th>
                                <th>Cuối kì</th>
                                {/* <th colSpan="2" >
                                    <Table responsive style ={{margin:"0px", padding:"0px"}}>
                                        <thead>
                                            <tr>
                                                <th colSpan="2" >Điểm trung bình</th>
                                            </tr>
                                            <tr>
                                                <th style = {{width:"50%"}}>Thang 10</th>
                                                <th style = {{width:"50%"}}>Chữ</th>
                                            </tr>
                                        </thead>
                                    </Table>
                                </th> */}
                            </tr>
                        </thead>
                        <tbody>
                            <tr className = "edit-score-row">
                                <td contentEditable = {true} suppressContentEditableWarning={true}>{info.BT}</td>
                                <td contentEditable = {true} suppressContentEditableWarning={true}>{info.GK}</td>
                                <td contentEditable = {true} suppressContentEditableWarning={true}>{info.CK}</td>
                                {/* <td >10</td>
                                <td >A+</td> */}
                            </tr>
                        </tbody>
                    </Table>
                </Modal.Body>
                <Modal.Footer>
                    <Button onClick={() => setLgShow(false)} title = "Save"/>
                </Modal.Footer>
            </Modal>
            <div className = "search-container">
                <ValidateSelect name ="major" lable = "Lớp">
                    <option className = "default-option" value="">-Chọn Lớp-</option>
                    <option value="Công nghệ thông tin">Công nghệ thông tin</option>
                    <option value="Công nghệ sinh học">Công nghệ sinh học</option>
                    <option value="Kiến trúc">Kiến trúc</option>
                    <option value="Nhiệt điện">Nhiệt điện</option>
                </ValidateSelect>
                <ValidateSelect name ="major" lable = "Môn học">
                    <option className = "default-option" value="">-Chọn ngành-</option>
                    <option value="Công nghệ thông tin">Công nghệ thông tin</option>
                    <option value="Công nghệ sinh học">Công nghệ sinh học</option>
                    <option value="Kiến trúc">Kiến trúc</option>
                    <option value="Nhiệt điện">Nhiệt điện</option>
                </ValidateSelect>
                <Button title="Dữ liệu"/>
            </div>
            <div className ="detail-score" >
                <h5 >Điểm chi tiết</h5>
                <Table className = "detail-score-table" responsive bordered hover>
                    <thead >
                        <tr>
                            <th>STT</th>
                            <th>Tên sinh viên</th>
                            <th>BT</th>
                            <th>GK</th>
                            <th>CK</th>
                            <th colSpan="2" >
                                <Table responsive style ={{margin:"0px", padding:"0px"}}>
                                    <thead>
                                        <tr>
                                            <th colSpan="2" >Điểm trung bình</th>
                                        </tr>
                                        <tr>
                                            <th style = {{width:"50%"}}>Thang 10</th>
                                            <th style = {{width:"50%"}}>Chữ</th>
                                        </tr>
                                    </thead>
                                </Table>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr onClick={handleShowModal}>
                            <th>1/2021</th>
                            <td>Trần Phước Thịnh</td>
                            <td>10</td>
                            <td>10</td>
                            <td>10</td>
                            <td >10</td>
                            <td >A+</td>
                        </tr>
                        <tr onClick={handleShowModal}>
                            <th>1/2021</th>
                            <td>Trần Phước Thịnh 1</td>
                            <td>9</td>
                            <td>9</td>
                            <td>9</td>
                            <td>9</td>
                            <td>A</td>
                        </tr>
                        <tr onClick={handleShowModal}>
                            <th>1/2021</th>
                            <td>Trần Phước Thịnh 2</td>
                            <td>8</td>
                            <td >8</td>
                            <td>8</td>
                            <td>8</td>
                            <td>B+</td>
                        </tr>
                    </tbody>
                </Table>
            </div>
        </div>
    )
}
export default ScoreTeacher