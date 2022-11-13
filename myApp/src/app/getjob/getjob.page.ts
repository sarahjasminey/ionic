import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-getjob',
  templateUrl: './getjob.page.html',
  styleUrls: ['./getjob.page.scss'],
})
export class GetjobPage implements OnInit {
  jobs:any;

  constructor(private http: HttpClient) { }

  ngOnInit() {

    
    this.http.get("https://teddiapp.com/app/api/ionic_user/getjob")
    .subscribe((data:any)=>{
          this.jobs = data.data.job;
          console.log(data);
          // console.log(data.data);
        })

  }

}
