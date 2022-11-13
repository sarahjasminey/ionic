import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from "@angular/forms";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router,NavigationExtras } from '@angular/router';
import * as moment from 'moment';



@Component({
  selector: 'app-regform',
  templateUrl: './regform.page.html',
  styleUrls: ['./regform.page.scss'],
})
export class RegformPage implements OnInit {
  ionicForm: FormGroup;
  defaultDate = "1987-06-30";
  isSubmitted = false;
  id:number;
  usertype:any;
  userid:any;
  aadharimage:any;
  panimage:any;
  currentTime:any;
  year:any;


    constructor(private route: ActivatedRoute, private router: Router,public formBuilder: FormBuilder,private http: HttpClient) {

    this.route.queryParams.subscribe(params => {
      //console.log(this.router.getCurrentNavigation().extras.queryParams);
      if (this.router.getCurrentNavigation().extras.queryParams) {
        this.usertype = this.router.getCurrentNavigation().extras.queryParams.type;  
        this.userid = this.router.getCurrentNavigation().extras.queryParams.id;
      }
      else{
         // alert('no id'); 
          this.userid =5;       
      }
    });


  }
  httpHeader = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  
     
  ngOnInit() {
    this.ionicForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      dob: ['', [Validators.required]],
      address: ['', [Validators.required]],
      // email: ['', [Validators.required]],
      email: new FormControl('', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')
      ])),
      pan: ['', [Validators.required]],
      aadhar: ['', [Validators.required]],
      gender: ['', [Validators.required]],
     


    })
  }
  
   get errorControl() {
    return this.ionicForm.controls;
  }

  keyup(){
    this.currentTime = new Date();
    this.year = this.currentTime.getFullYear();
    this.year  = this.year - 18;   
    // alert(this.year );

  }

  uploadaadhar(str:any)
  {
    const formData = new FormData();
    this.aadharimage=str.target.files[0];
    formData.append('files[]', this.aadharimage);
    console.log(formData,this.aadharimage);
     this.http.post("https://teddiapp.com/app/api/ionic_user/user_upload_aadhar",formData)
 .subscribe((data:any)=>{
       console.log(data);
     })
  
    console.log(str);
  }

  uploadpan(str:any)
  {
    const formData = new FormData();
    this.panimage=str.target.files[0];
    formData.append('files[]', this.panimage);
    console.log(formData,this.panimage);
     this.http.post("https://teddiapp.com/app/api/ionic_user/user_upload_pan",formData)
 .subscribe((data:any)=>{
       console.log(data);
     })
  
    console.log(str);
  }




  onSubmit() {
    this.isSubmitted = true;
    if (!this.ionicForm.valid) {
      console.log('Please provide all the required values!')
      return false;
    } else {




      const headeroptions = {   
        'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
        }
      
        let navigationExtras: NavigationExtras = {
          queryParams: {
            personaldata:JSON.stringify(this.ionicForm.value),
            id: this.userid
          }
        };
        this.router.navigate(['profdetail'], navigationExtras);
     
    }

    
  }


}





  



