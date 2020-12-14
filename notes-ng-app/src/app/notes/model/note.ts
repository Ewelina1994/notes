import {Notebook} from "./notebook";

export interface Note {
  id: String;
  title: String;
  text: String;
  notebook: Notebook;
  lastModifieddOn: String;
}
