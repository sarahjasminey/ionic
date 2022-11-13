import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-profdetail',
  templateUrl: './profdetail.page.html',
  styleUrls: ['./profdetail.page.scss'],
})
export class ProfdetailPage implements OnInit {
  profForm: FormGroup;
  defaultDate = "1987-06-30";
  isSubmitted = false;
  id:number;
  personaldata:any;
  work_from:any;
  company_name:any;
  company_address:any;

    constructor(private route: ActivatedRoute, private router: Router,public formBuilder: FormBuilder,private http: HttpClient) {

    this.route.queryParams.subscribe(params => {
      if (this.router.getCurrentNavigation().extras.queryParams) {
        this.personaldata = this.router.getCurrentNavigation().extras.queryParams.personaldata;
        this.id = this.router.getCurrentNavigation().extras.queryParams.id;  
        console.log(this.personaldata+'-------');
      }
         
    
    });


  }
  httpHeader = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
     
  ngOnInit() {
    this.profForm = this.formBuilder.group({
      work_from:['',[Validators.required]],
      company_name: ['', [Validators.required]],
      company_address: ['', [Validators.required]],
    
    })
  }
  
   get errorControl() {
    return this.profForm.controls;
  }
  onSubmit() {
    this.isSubmitted = true;
    if (!this.profForm.valid) {
      console.log('Please provide all the required values!')
      return false;
    } else {




      const headeroptions = {   
        'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
        }
      console.log(this.personaldata);
        let params = 'data=' + JSON.stringify(this.profForm.value)+'&personaldata='+this.personaldata+'&id='+this.id;
      
       return this.http.post<any>('https://teddiapp.com/app/api/ionic_user/add/',params,  {headers: headeroptions},).subscribe(data => {
         console.log("Subscribed Data: "+data);
          this.router.navigate(['category']);
       
        
      },
       error => {
         console.log('error: ' + error.error);
         console.log('Name: ' + error.name);
         console.log('Message: ' + error.message);
         console.log('Status: ' + error.status);
       }); 
    }

    
  }
}