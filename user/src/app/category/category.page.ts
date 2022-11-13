import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Router,NavigationExtras } from '@angular/router';



@Component({
  selector: 'app-category',
  templateUrl: './category.page.html',
  styleUrls: ['./category.page.scss'],
})
export class CategoryPage implements OnInit {
category:any;
radioValue:any;
selectedValue = 'Default';

  constructor(private route: ActivatedRoute, private router: Router,private http: HttpClient) { }

  ngOnInit() {

    this.http.get("https://teddiapp.com/app/api/ionic_champ/category")
    .subscribe((data:any)=>{
          this.category = data.data.categories;
          console.log(data);
          console.log(data.data);
        })
     

  }
  radioGroupChange(event) {
    console.log(event.target.value);
    this.selectedValue = event.target.value;
    let navigationExtras: NavigationExtras = {
      queryParams: {
        id:this.selectedValue,
      }
    };
    this.router.navigate(['create-job'], navigationExtras);
  }


  showValue(val){
    console.log(val);
  }
  

}
