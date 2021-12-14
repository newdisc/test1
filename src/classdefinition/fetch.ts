
import { ClassDefinition } from "./type";
import { getAllClassDefinitionInit, getAllClassDefinitionDone } from "./action";

export async function getAllClassDefinition(dispatch:any) {
    dispatch(getAllClassDefinitionInit())
    const result = await fetch('/test/api/classdef/getAll')
    const stat = result.statusText
    if (!result.ok) {
        dispatch(getAllClassDefinitionDone('Error Fetching: ' + stat, []))
        return
    }
    const js: ClassDefinition[] = await result.json();
    //console.log("Status: ", stat)
    dispatch(getAllClassDefinitionDone('', js))
    //console.log(js)
}