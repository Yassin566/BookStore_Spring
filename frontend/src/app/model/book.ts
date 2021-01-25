import { ImageModel } from "./image-model";

export class Book {

    Id: any;
    title: string = "";
    author: string = "";
    price: any;
    releaseDate: Date | undefined;
    image: ImageModel = new ImageModel();

}
