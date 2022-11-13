import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router,NavigationExtras } from '@angular/router';

@Component({
  selector: 'app-create-job',
  templateUrl: './create-job.page.html',
  styleUrls: ['./create-job.page.scss'],
})
export class CreateJobPage implements OnInit {
  jobForm: FormGroup;
  defaultDate = "1987-06-30";
  isSubmitted = false;
  id:number;
  category:any;
  category_id:any;
  sub_categotry:any;
  title:any;
  description:any;
  persons:any;
  price:any;
  data:any;
  cat_id:any;
  cat_name:any;

    constructor(private route: ActivatedRoute, private router: Router,public formBuilder: FormBuilder,private http: HttpClient) {

      
    this.route.queryParams.subscribe(params => {
            this.data = this.router.getCurrentNavigation().extras.queryParams;
            var catdetails = this.data.id.split('-');
            this.cat_id=catdetails[0];
            this.cat_name = catdetails[1];

        });

  }
  httpHeader = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
     
  ngOnInit() {
    this.jobForm = this.formBuilder.group({
      category:['',[Validators.required]],

      title:['',[Validators.required]],
      description:['',[Validators.required]],
      // category_id: ['', []],
      persons: ['', [Validators.required]],
      price: ['', [Validators.required]],
    });

    this.jobForm.controls.category.setValue(this.cat_name);
    // this.jobForm.controls.category_id.setValue(this.cat_id);



    const headeroptions = {   
      'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
      }
    
     return this.http.get<any>('https://teddiapp.com/app/api/ionic_user/category/',  {headers: headeroptions},).subscribe(data => {
       console.log("Subscribed Data: "+JSON.stringify(data));      
    },
     error => {
       console.log('error: ' + error.error);
       console.log('Name: ' + error.name);
       console.log('Message: ' + error.message);
       console.log('Status: ' + error.status);
     }); 


  }
  
   get errorControl() {
    return this.jobForm.controls;
  }
  onSubmit() {
    this.isSubmitted = true;
    if (!this.jobForm.valid) {
      console.log('Please provide all the required values!')
      return false;
    } else {

      console.log(this.jobForm.value);

       const headeroptions = {   
         'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8',
         }
         let params = 'data=' + JSON.stringify(this.jobForm.value)+'&id='+this.cat_id;
      console.log(params);
        return this.http.post<any>('https://teddiapp.com/app/api/ionic_user/job/',params,  {headers: headeroptions},).subscribe(data => {
          console.log(data);
        //   if(data.status=="success"){
        //     alert('job posted successfully');
        // //   this.router.navigate(['create-job']);
        //   }
        //   else{
        //    console.log(data.status);
        //   }    
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
