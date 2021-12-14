
import {GETALL_CLASSDEFINITION_DONE, GETALL_CLASSDEFINITION_INIT} from './type'

export const initialState = {
    classes: [],
    pending: true,
    error: null
}

export function classDefinitionReducer(state = initialState, action:any) {
    //console.log("Reducer called: ", state, action)
    switch (action.type) {
        case GETALL_CLASSDEFINITION_INIT:
            return {
                ...state,
                pending: true,
                error: null,
                classes: []
            };
            case GETALL_CLASSDEFINITION_DONE:
                return {
                    ...state,
                    pending: false,
                    error: action.error,
                    classes: action.payload
                };
            default:
            return state;
    }
}

export default classDefinitionReducer;
