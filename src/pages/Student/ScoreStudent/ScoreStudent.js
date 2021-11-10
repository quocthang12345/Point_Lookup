import "./ScoreStudent.css"
import {Table} from 'react-bootstrap';
import Header from "../../../components/Header/Header"
import {useState} from 'react'
import { Link } from "react-router-dom";

const ScoreStudent = () =>{
    return(
    <div className="score-student-page">
        <Header>
        <div className = "nav-item-header"><b>Cá nhân</b>
                <div className = "dropdown-content">
                    <Link to ="/studentprofile">Thông tin cá nhân</Link>
                    <Link>Điểm thi</Link>
                    <Link to ="/schedule">Lịch học, lịch thi</Link>
                </div>
            </div>
            <div className = "nav-item-header"><b>Yêu cầu</b>
                <div className = "dropdown-content">
                    <Link >Yêu cầu nhà trường</Link>
                    <Link >Yêu cầu nhà trường</Link>
                    <Link >Yêu cầu nhà trường</Link>
                    
                </div>
            </div>
            <div className = "nav-item-header"><b>Khác</b>
                <div className = "dropdown-content">
                    <Link>Chức năng 1</Link>  
                    <Link>Chức năng 2</Link>
                    <Link>Chức năng 3</Link>
                </div>
            </div>
        </Header>
        <h2 className ="score-page-title">Kết quả học tập</h2>
        <div className="average-score">
            <h5>Điểm tổng hợp </h5>
            <Table responsive bordered hover>
                <thead >
                    <tr>
                        <th >Học kì</th>
                        <th >Số môn học</th>
                        <th >Điểm trung bình ở học kì</th>
                        <th >Điểm trung bình tích lũy</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th >1/2021</th>
                        <td>6</td>
                        <td>9</td>
                        <td>9</td>
                    </tr>
                    <tr>
                        <th >2/2021</th>
                        <td>5</td>
                        <td>10</td>
                        <td>9.8</td>
                    </tr>
                    <tr>
                        <th >3/2021</th>
                        <td >10</td>
                        <td>8</td>
                        <td>8.7</td>
                    </tr>
                </tbody>
            </Table>
        </div>
        <div className ="detail-score" >
            <h5 >Điểm chi tiết</h5>
            <Table className = "detail-score-table" responsive bordered hover>
                <thead >
                    <tr>
                        <th>Học kì</th>
                        <th>Môn học</th>
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
                    <tr>
                        <th>1/2021</th>
                        <td>Toán ứng dụng công nghệ Thông tin</td>
                        <td>10</td>
                        <td>10</td>
                        <td>10</td>
                        <td >10</td>
                        <td >A+</td>
                    </tr>
                    <tr>
                        <th>1/2021</th>
                        <td>Cấu trúc dữ liệu</td>
                        <td>9</td>
                        <td>9</td>
                        <td>9</td>
                        <td>9</td>
                        <td>A</td>
                    </tr>
                    <tr>
                        <th>1/2021</th>
                        <td>Hệ thống thông tin</td>
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
export default ScoreStudent