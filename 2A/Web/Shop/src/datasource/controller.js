import { towns, characs } from './data'

// controllers: mime the API behavior

function getAllTowns() {
  return {error: 0, data: towns}
}

function getAllCharacs() {
  return {error: 0, data: characs}
}

export default{
  getAllTowns,
  getAllCharacs
}