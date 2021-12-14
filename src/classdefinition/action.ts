
import { ClassDefinition, GETALL_CLASSDEFINITION_DONE, GETALL_CLASSDEFINITION_INIT } from "./type";

export function getAllClassDefinitionInit() {
    //console.log("ClassDefinitions retrieve")
    return {
        type: GETALL_CLASSDEFINITION_INIT
    };
}
export function getAllClassDefinitionDone(error: string, data: ClassDefinition[]) {
    //console.log("ClassDefinitions: ")
    //console.log(data)
    //console.log("StatusError: ", error)
    return {
        type: GETALL_CLASSDEFINITION_DONE,
        error,
        payload: data
    };
}
