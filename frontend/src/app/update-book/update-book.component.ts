import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Book } from '../model/book';
import { AuthService } from '../service/auth.service';
import { BookServiceService } from '../service/book-service.service';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {
  model: any;

  id: any;
  constructor(public bookService: BookServiceService, public route: ActivatedRoute, public router: Router, public auth: AuthService) {
    if (!this.auth.isAdmin) {
      this.router.navigate(['app-get-all-books']);
    }
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params["id"];

    this.getBook(this.id);
  }

  onSubmit() {
    this.bookService.updateBook(this.model)
      .subscribe(resp => {
        console.log(this.model.price);

      })
  }

  getBook(id: number) {
    this.bookService.getBook(id)
      .subscribe(resp => {
        this.model = resp;
      })

  }

  public onFileChanged(event: any) {
    //Select File
    this.bookService.selectedFile = event.target.files[0];
    this.bookService.onUpload();
  }

}
