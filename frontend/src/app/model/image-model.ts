import { Byte } from "@angular/compiler/src/util";

export class ImageModel {
    id: any;
    name: string = "";
    type: string = "";
    pic: Byte[] | undefined;
}
